package com.xkings.pokemontd.component.attack.projectile.data;

/**
 * Created by Tomas on 10/13/13.
 */
public class IncreasingDamageData extends EffectData {
    private final float damageIncreasing;
    private final float max;
    private final float duration;

    public IncreasingDamageData(String effect, float damageIncreasing, float duration, float max) {
        super(effect, 1);
        this.damageIncreasing = damageIncreasing;
        this.duration = duration;
        this.max = max;
    }

    public float getDamageIncreasing() {
        return damageIncreasing;
    }

    public float getDuration() {
        return duration;
    }

    public float getMax() {
        return max;
    }

    @Override
    public String getEffectName() {
        return "Increasing damage";
    }

    @Override
    public String getEffectDescription(float speed, float damage) {
        return super.getEffectDescription(speed, damage)+  "Increasing damage";
    }
}
