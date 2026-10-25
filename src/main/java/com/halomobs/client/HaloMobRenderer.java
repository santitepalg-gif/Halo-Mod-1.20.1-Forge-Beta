package com.halomobs.client;

import com.halomobs.entity.HaloMobEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HaloMobRenderer extends GeoEntityRenderer<HaloMobEntity> {
    public HaloMobRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new HaloMobModel());
        this.shadowRadius = 0.5f;
    }

    /** La animación "dead" ya tumba el modelo; evitamos el giro vanilla de 90°. */
    @Override
    protected float getDeathMaxRotation(HaloMobEntity animatable) { return 0f; }
}
