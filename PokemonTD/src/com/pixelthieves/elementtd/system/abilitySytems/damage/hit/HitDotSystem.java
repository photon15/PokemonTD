package com.pixelthieves.elementtd.system.abilitySytems.damage.hit;

import com.artemis.Entity;
import com.pixelthieves.elementtd.component.attack.effects.DotEffect;
import com.pixelthieves.elementtd.component.attack.projectile.data.DotData;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitDotSystem extends HitEffectSystem<DotData, DotEffect> {

    public HitDotSystem() {
        super(DotData.class, DotEffect.class);
    }


    @Override
    protected void initialize() {
        super.initialize();
        // DISCUS this on stackoverflow !
        setAoe(new AoeSystem() {
        });
    }

    @Override
    protected DotEffect resetEffect(Entity e, Entity target, DotEffect effect, DotData effectData) {
        effect.set(effectData.getEffect(), effectData.getInterval(), effectData.getIterations(),
                damageMapper.get(e).getDamage() * effectData.getDamage());
        return effect;
    }

    @Override
    protected DotEffect createEffect(Entity e, Entity target, DotData effectData) {
        return resetEffect(e, target, new DotEffect(), effectData);
    }
}
