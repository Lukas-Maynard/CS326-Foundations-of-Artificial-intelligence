package core_algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * implement elements that are independent of any specific problem
 *
 */
public abstract class GeneticAlgorithm<G> {
    private final int MAX_GEN;
    private final double MUTATION_RATE;
    private final double ELITISM;

    public GeneticAlgorithm(int maxGen, double mRate, double elitism){
        this.MAX_GEN = maxGen;
        this.MUTATION_RATE = mRate;
        this.ELITISM = elitism;
    }

    public Individual<G> evolve (List<Individual<G>> initPopulation){
        List<Individual<G>> population = initPopulation;
        for(int generation = 1; generation <= MAX_GEN; generation++) {
            List<Individual<G>> offspring = new ArrayList<>();
            for (int i = 0; i < population.size(); i++) {
                Individual<G> p1 = selectAParent(population);
                Individual<G> p2 = selectAParent(population, p1);
                Individual<G> child = reproduce(p1, p2);
                if (new Random().nextDouble() <= MUTATION_RATE) {
                    child = mutate(child);
                }
                offspring.add(child);
            }
            Collections.sort(population);
            Collections.sort(offspring);
            List<Individual<G>> newPopulation = new ArrayList<>();
            int e = (int) (ELITISM * population.size());
            for (int i = 0; i < e; i++) {
                newPopulation.add(population.get(i));
            }
            for (int i = 0; i < population.size() - e; i++) {
                newPopulation.add(offspring.get(i));
            }
            population = newPopulation;
        }//end of outer for loop

        Collections.sort(population);
        return population.get(0);
    }

    public abstract Individual<G> reproduce (
            Individual<G> p1, Individual<G> p2);
    public abstract Individual<G> mutate (Individual<G> i);
    public abstract double calcFitnessScore(List<G> chromosome);

    public Individual<G> selectAParent(List<Individual<G>> population) {
        // Calculate total fitness of the population
        double totalFitness = population.stream()
                .mapToDouble(Individual::getFitnessScore)
                .sum();

        // Generate a random number between 0 and the total fitness
        double randomFitness = new Random().nextDouble() * totalFitness;

        // Iterate through the population and select the individual where the cumulative fitness exceeds the randomFitness
        double cumulativeFitness = 0;
        for (Individual<G> individual : population) {
            cumulativeFitness += individual.getFitnessScore();
            if (cumulativeFitness >= randomFitness) {
                return individual;
            }
        }
        // This should never happen under normal circumstances
        return population.get(population.size() - 1);
    }

    public Individual<G> selectAParent(List<Individual<G>> population, Individual<G> p) {
        Individual<G> parent;
        do {
            parent = selectAParent(population);
        } while (parent.equals(p)); // Keep selecting until the chosen parent is different from p
        return parent;
    }
}
