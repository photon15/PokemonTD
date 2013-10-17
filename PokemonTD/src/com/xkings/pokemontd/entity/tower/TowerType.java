package com.xkings.pokemontd.entity.tower;

import com.xkings.core.main.Assets;
import com.xkings.pokemontd.Treasure;
import com.xkings.pokemontd.component.attack.AbilityComponent;
import com.xkings.pokemontd.entity.datatypes.CommonDataType;

import java.util.*;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import static com.xkings.pokemontd.entity.tower.TowerName.*;

/**
 * Created by Tomas on 10/5/13.
 */
public class TowerType implements CommonDataType {

    private static Map<TowerName, List<TowerType>> map;

    private final TowerName name;
    private final AtlasRegion texture;
    private final AtlasRegion blockedTexture;
    private final float speed;
    private final float size;
    private final float damage;
    private final float range;
    private final AbilityComponent attack;
    private final Treasure cost;

    public TowerType(TowerName name, float speed, float size, float damage, float range, AbilityComponent attack,
                     Treasure cost) {
        this.name = name;
        this.texture = Assets.getTexture(name.toString().toLowerCase());
        this.blockedTexture = Assets.getTexture("blocked");
        this.speed = speed;
        this.size = size;
        this.damage = damage;
        this.range = range;
        this.attack = attack;
        this.cost = cost;
    }


    public AtlasRegion getTexture() {
        return texture;
    }

    public float getSpeed() {
        return speed;
    }

    public float getSize() {
        return size;
    }

    public AbilityComponent getAttack() {
        return attack;
    }

    public float getRange() {
        return range;
    }

    public Treasure getCost() {
        return cost;
    }

    public AtlasRegion getBlockedTexture() {
        return blockedTexture;
    }

    public TowerName getName() {
        return name;
    }

    public float getDamage() {
        return damage;
    }

    public static Map<TowerName, List<TowerType>> getHierarchy() {
        if (map == null) {
            map = new HashMap<TowerName, List<TowerType>>();
            map.put(null, getTypes(Needle, Scratch));
            map.put(Needle, getTypes(Pinch));
            map.put(Pinch, getTypes(Sting));
            map.put(Sting, getTypes());
            map.put(Scratch, getTypes(Bite));
            map.put(Bite, getTypes(Smash));
            map.put(Smash, getTypes());
            map = Collections.unmodifiableMap(map);
        }
        return map;
    }

    private static Map<TowerName, TowerType> towerTypeMap = new TowerTypeBuilder().build();

    private static List<TowerType> getTypes(TowerName... names) {
        List<TowerType> list = new ArrayList<TowerType>();
        for (TowerName towerName : names) {
            list.add(towerTypeMap.get(towerName));
        }
        return Collections.unmodifiableList(list);
    }
}