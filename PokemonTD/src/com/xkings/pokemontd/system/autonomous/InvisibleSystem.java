package com.xkings.pokemontd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.xkings.pokemontd.component.CreepAbilityComponent;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.VisibleComponent;
import com.xkings.pokemontd.entity.creep.CreepAbilityType;

/**
 * Created by Tomas on 10/4/13.
 */
public class InvisibleSystem extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<VisibleComponent> visibleMapper;
    @Mapper
    ComponentMapper<CreepAbilityComponent> creepAbilityMapper;
    private boolean invisible;

    public InvisibleSystem() {
        super(Aspect.getAspectForAll(HealthComponent.class, CreepAbilityComponent.class));
    }

    @Override
    protected void process(Entity e) {
        if (creepAbilityMapper.get(e).getCreepAbilityType().equals(CreepAbilityType.INVISIBLE)) {
            visibleMapper.get(e).setVisible(!invisible);
        }
    }

    public void start(boolean invisible) {
        this.invisible = invisible;
        this.process();
    }

}
