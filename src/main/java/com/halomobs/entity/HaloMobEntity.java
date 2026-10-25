package com.halomobs.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class HaloMobEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final HaloMobKind kind;

    public HaloMobEntity(EntityType<? extends HaloMobEntity> type, Level level) {
        super(type, level);
        var key = ForgeRegistries.ENTITY_TYPES.getKey(type);
        this.kind = key == null ? HaloMobKind.ELITE : HaloMobKind.byId(key.getPath());
    }

    public HaloMobKind getKind() { return kind; }

    public static AttributeSupplier.Builder createAttributes(HaloMobKind k) {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, k.health)
                .add(Attributes.ATTACK_DAMAGE, k.damage)
                .add(Attributes.MOVEMENT_SPEED, k.speed)
                .add(Attributes.FOLLOW_RANGE, 32.0);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
        goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8));
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    /** Alarga la muerte a 2 s para que se vea la animación "dead". */
    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime >= 40 && !this.level().isClientSide() && !this.isRemoved()) {
            this.level().broadcastEntityEvent(this, (byte) 60);
            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "main", 5, this::mainPredicate));
        controllers.add(new AnimationController<>(this, "attack", 0, this::attackPredicate));
    }

    private PlayState mainPredicate(AnimationState<HaloMobEntity> state) {
        if (isDeadOrDying()) {
            if (kind.dead == null) return PlayState.STOP;
            return state.setAndContinue(RawAnimation.begin().thenPlayAndHold(kind.dead));
        }
        String anim = state.isMoving() ? (getTarget() != null && kind.run != null ? kind.run : kind.walk) : kind.idle;
        if (anim == null) return PlayState.STOP;
        return state.setAndContinue(RawAnimation.begin().thenLoop(anim));
    }

    private PlayState attackPredicate(AnimationState<HaloMobEntity> state) {
        if (kind.attack != null && this.swinging && !isDeadOrDying()
                && state.getController().getAnimationState() == AnimationController.State.STOPPED) {
            state.getController().forceAnimationReset();
            state.setAnimation(RawAnimation.begin().then(kind.attack, software.bernie.geckolib.core.animation.Animation.LoopType.PLAY_ONCE));
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() { return cache; }
}
