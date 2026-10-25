package com.halomobs.entity;

import java.util.Locale;

/** Define cada mob: tamaño, stats y nombres de animación (tomados de los .animation.json de Bedrock). */
public enum HaloMobKind {
    //      id            ancho alto  vida dano vel   idle                            walk                           run                               attack                          dead                          huevo1    huevo2
    ELITE("elite",        0.7f, 2.3f, 60, 8, 0.30, "animation.elite.general",         "animation.elite.moving",      "animation.elite.running",        "animation.elite.attack1",      "animation.elite.dead",       0x2A5CAA, 0xA8C8FF),
    GRUNT("grunt",        0.5f, 1.2f, 20, 3, 0.26, "animation.halo_grunt.general",    "animation.halo_grunt.moving", "animation.halo_grunt.running",   "animation.halo_grunt.meele",   "animation.halo_grunt.dead",  0xD0782A, 0xFFD49A),
    JACKAL("jackal",      0.6f, 1.5f, 24, 4, 0.30, "animation.halo_jackals.general", "animation.halo_jackals.moving","animation.halo_jackals.running","animation.halo_jackals.attack1","animation.halo_jackals.dead",0x5C6B3A, 0xC9D8A0),
    HUNTER("hunter",      1.1f, 2.8f, 120, 14, 0.22, null, null, null, null, null,                                                                         0x3B3F6B, 0xE0B040),
    BRUTE_CAPITAN("brute_capitan", 0.8f, 2.2f, 70, 9, 0.28, "animation.brute_capitan.general","animation.brute_capitan.moving","animation.brute_capitan.running","animation.brute_capitan.attack1","animation.brute_capitan.dead",0x6B2A2A, 0xD08A5A),
    BRUTE_CACIQUE("brute_cacique", 0.9f, 2.4f, 90, 12, 0.28, "animation.brute_cacique.general","animation.brute_cacique.running","animation.brute_cacique.running","animation.brute_cacique.attack1","animation.brute_cacique_d.base1",0x4A1F1F, 0xE0B070);

    public final String id;
    public final float width, height;
    public final double health, damage, speed;
    public final String idle, walk, run, attack, dead;
    public final int eggPrimary, eggSecondary;

    HaloMobKind(String id, float w, float h, double health, double damage, double speed,
                String idle, String walk, String run, String attack, String dead, int e1, int e2) {
        this.id = id; this.width = w; this.height = h;
        this.health = health; this.damage = damage; this.speed = speed;
        this.idle = idle; this.walk = walk; this.run = run; this.attack = attack; this.dead = dead;
        this.eggPrimary = e1; this.eggSecondary = e2;
    }

    public static HaloMobKind byId(String id) {
        for (HaloMobKind k : values()) if (k.id.equals(id.toLowerCase(Locale.ROOT))) return k;
        return ELITE;
    }
}
