package optimization_solutions;

import core_algorithms.GeneticAlgorithm;
import core_algorithms.Individual;
import optimization_problems.TSP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implement elements that are problem specific
 *
 */
public class GeneticAlgorithm_TSP
        extends GeneticAlgorithm<Integer> {
    private final TSP problem;

    public GeneticAlgorithm_TSP(
            int maxGen, double mRate, double elitism, TSP problem){
        super(maxGen, mRate, elitism);
        this.problem = problem;
    }

    public double calcFitnessScore(
            List<Integer> chromosome){
        return 1/problem.cost(chromosome);
    }

    public Individual<Integer> reproduce(Individual<Integer> p1, Individual<Integer> p2) {
        List<Integer> parent1Chromosome = p1.getChromosome();
        List<Integer> parent2Chromosome = p2.getChromosome();
        int chromosomeSize = parent1Chromosome.size();

        int startPos = (int) (Math.random() * chromosomeSize);
        int endPos = (int) (Math.random() * (chromosomeSize - startPos)) + startPos;
        List<Integer> childChromosome = new ArrayList<>(Collections.nCopies(chromosomeSize, null));
        for (int i = startPos; i <= endPos; i++) {
            childChromosome.set(i, parent1Chromosome.get(i));
        }

        int childIndex = 0;
        for (int i = 0; i < chromosomeSize; i++) {
            if (childIndex >= startPos && childIndex <= endPos) {
                childIndex = endPos + 1;
            }
            int gene = parent2Chromosome.get(i);
            if (!childChromosome.contains(gene)) {
                while (childChromosome.get(childIndex) != null) {
                    childIndex = (childIndex + 1) % chromosomeSize;
                }
                childChromosome.set(childIndex, gene);
            }
        }
        return new Individual<>(childChromosome, calcFitnessScore(childChromosome));
    }


    public Individual<Integer> mutate(Individual<Integer> individual) {
        List<Integer> chromosome = new ArrayList<>(individual.getChromosome());
        int size = chromosome.size();

        // Random select pos
        int pos1 = (int) (Math.random() * size);
        int pos2;
        do {
            pos2 = (int) (Math.random() * size);
        } while (pos1 == pos2);

        // Swap the genes at the rand pos
        Collections.swap(chromosome, pos1, pos2);

        return new Individual<>(chromosome, calcFitnessScore(chromosome));
    }


    public List<Individual<Integer>> generateInitPopulation(
            int popSize, int numCities ){
        List<Individual<Integer>> population =
                new ArrayList<>(popSize);
        for(int i=0; i<popSize; i++){
            List<Integer> chromosome = new ArrayList<>(numCities);
            for(int j=0; j<numCities; j++){
                chromosome.add(j);
            }
            Collections.shuffle(chromosome);
            Individual<Integer> indiv = new Individual<>(
                    chromosome, calcFitnessScore(chromosome));
            population.add(indiv);
        }
        return population;
    }

    public static void main(String[] args) {
        int MAX_GEN = 80;
        double MUTATION_RATE = 0.05;
        int POPULATION_SIZE = 100;
        int NUM_CITES = 26; //choose from 5, 6, 17, 26
        double ELITISM = 0.2;

        TSP problem = new TSP(NUM_CITES);

        GeneticAlgorithm_TSP agent = new GeneticAlgorithm_TSP(
                MAX_GEN, MUTATION_RATE, ELITISM, problem);

        Individual<Integer> best = agent.evolve(agent.generateInitPopulation(POPULATION_SIZE, NUM_CITES));

        System.out.println(best);
        System.out.println(problem.cost(best.getChromosome()));
    }
}
