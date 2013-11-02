package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.xkings.core.component.SizeComponent;
import com.xkings.core.component.TargetComponent;
import com.xkings.core.component.Time;
import com.xkings.core.component.TimeComponent;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.PathComponent;
import com.xkings.pokemontd.component.WaveComponent;
import com.xkings.pokemontd.component.attack.effects.buff.BuffableDamageComponent;
import com.xkings.pokemontd.component.attack.projectile.data.BubbleData;
import com.xkings.pokemontd.system.resolve.IntersectEnemySystem;

/**
 * Created by Tomas on 10/4/13.
 */
public class BubbleSystem extends EntityProcessingSystem {

    private final IntersectEnemySystem intersectSystem;
    @Mapper
    ComponentMapper<BuffableDamageComponent> damageMapper;
    @Mapper
    ComponentMapper<BubbleData> bubbleMapper;
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<TimeComponent> timeMapper;
    @Mapper
    ComponentMapper<PathComponent> pathMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    private BubbleData bubble;
    private float damage;


    public BubbleSystem(World world) {
        super(Aspect.getAspectForAll(TargetComponent.class, BubbleData.class));
        intersectSystem = new IntersectEnemySystem(WaveComponent.class) {

            @Override
            protected void intersect(Entity e) {
                System.out.println("B: "+  sizeMapper.get(e).getPoint());
                System.out.println( "A: "+  sizeMapper.get(e).getPoint());
                healthMapper.get(e).getHealth().decease(damage / bubble.getInterval());
            }
        };
        world.setSystem(intersectSystem, true);
    }


    @Override
    protected void process(Entity e) {
        if (pathMapper.get(e).getPath().isFinished()) {
            e.deleteFromWorld();
        }
        bubble = bubbleMapper.get(e);
        damage = damageMapper.get(e).getDamage();

        Time time = timeMapper.get(e).getTime(this.getClass());
        time.increase(world.getDelta());
        sizeMapper.get(e).getPoint().scl(bubble.getGrow());

        if (time.getAvailableTime() >= bubble.getInterval()) {
            time.decrease(bubble.getInterval());
            intersectSystem.start(e);
        }
    }
}
