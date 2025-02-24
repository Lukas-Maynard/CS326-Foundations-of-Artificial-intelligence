package core_algorithms;

import java.util.List;

public  abstract class Individual <G> implements Comparable<Individual<G>>{
    private List<G> chromosome;
    private double fitnessScore;

    public Individual(List<G> chromosome, double fitnessScore){
        this.chromosome = chromosome;
        this.fitnessScore = fitnessScore;
    }

    public List<G> getChromosome() {
        return chromosome;
    }

    public void setChromosome(List<G> chromosome) {
        this.chromosome = chromosome;
    }

    public double getFitnessScore() {
        return fitnessScore;
    }

    public void setFitnessScore(double fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "chromosome=" + chromosome +
                ", fitnessScore=" + fitnessScore +
                '}';
    }

    public int compareTo(Individual<G> i){
        return Double.compare(i.getFitnessScore(), this.getFitnessScore());
    }
}
