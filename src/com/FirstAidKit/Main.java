package com.FirstAidKit;

import java.util.*;

import com.FirstAidBox.Components.*;
import com.FirstAidKit.FirstAidExceptions.ExcessComponentException;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ExcessComponentsChain mainExcessComponents = new ExcessComponentsChain();
        mainExcessComponents.add(Map.entry(Plasters.class, Bandages.class));
        System.out.println(mainExcessComponents);
        System.out.println(
                mainExcessComponents.contains(Map.entry(Plasters.class, Bandages.class)) ||
                        mainExcessComponents.contains(Map.entry(Bandages.class, Plasters.class))
        );

        FirstAidBox _FirstAidBox = new FirstAidBox(new ArrayList<>(), "_FirstAidBox",  FirstAidBoxSize.LARGE, mainExcessComponents);
        try {

            _FirstAidBox.addFirstAidComponent(new Scissors(125))
                    .addFirstAidComponent(new Plasters(40))
                    .addFirstAidComponent(new Bandages(75))
                    .addFirstAidComponent(new Dressings(110))
                    .addFirstAidComponent(new Painkillers(250, "Ibuprofen"))
                    .addFirstAidComponent(new Painkillers(80, "Paracetamol"));

        } catch (com.FirstAidBox.FirstAidExceptions.ExcessComponentException e) {
            System.err.println(e.getMessage());
        }

        // find
        System.out.println("Specific item with 110 grams mass: " + basicFirstAidBox.find(comp -> comp.mass() == 110).get());
        System.out.println("All painkillers in basicFirstAidBox: " + basicFirstAidBox.findAll(component -> component instanceof Painkillers));

        FirstAidRepository _FirstAidRepository = new FirstAidRepository(basicFirstAidBox);

        System.out.println("The most heavy component (by mass): " + _FirstAidRepository
                .getMostHeavyComponent()
                .orElse(null)
        );
        System.out.println("Average mass of components: " +
                _FirstAidRepository
                        .getAverageMass()
                        .orElse(Double.NaN)
        );

        System.out.println("Mapped components: ");
        System.out.println(_FirstAidRepository
                .getMappedComponents(component -> component.mass() > 50 ? "heavy" : "light"));

        double averagePillsMass = _FirstAidRepository
                .getAllPills()
                .mapToInt(comp -> comp.mass())
                .average()
                .orElse(Double.NaN);
        System.out.println("Average Pills mass: " + averagePillsMass);

    }
}