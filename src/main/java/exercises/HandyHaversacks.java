package exercises;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandyHaversacks {
    public static final String FILENAME = "input/handy_haversacks_data.txt";
    Graph<String, DefaultWeightedEdge> graph;

    public HandyHaversacks() {
        graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
    }

    public void printAnswerPart1() {
        readFile();
        int pathCount = countPaths() - 1;
        System.out.println("The number of shiny gold options is: " + pathCount);
        int bagCount =  countBags("shiny gold");
        System.out.println("The number of bags is: " + bagCount);
    }

    private void readFile() {
        try {
            Scanner scanner = new Scanner(new File(FILENAME));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                processLine(line);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line) {
        String bagColorTarget = "^[a-z]+ [a-z]+ bags";
        Pattern pattern = Pattern.compile(bagColorTarget);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String match = line.substring(matcher.start(), matcher.end());
            String[] parts = match.split(" ");
            String bagColor = parts[0] + " " + parts[1];

            String innerBagTarget = "[0-9]+ [a-z]+ [a-z]+ bag";
            Pattern innerBagPattern = Pattern.compile(innerBagTarget);
            Matcher innerBagMatcher = innerBagPattern.matcher(line);
            while (innerBagMatcher.find()) {
                String innerBagMatch = line.substring(innerBagMatcher.start(), innerBagMatcher.end());
                String[] innerBagParts = innerBagMatch.split(" ");
                String innerBag = innerBagParts[1] + " " + innerBagParts[2];
                int bagCount = Integer.parseInt(innerBagParts[0]);
                addToGraph(bagColor, innerBag, bagCount);
            }
        }
    }

    private void addToGraph(String bagColor, String innerBag, int bagCount) {
        graph.addVertex(bagColor);
        graph.addVertex(innerBag);
        DefaultWeightedEdge edge = graph.addEdge(bagColor, innerBag);
        graph.setEdgeWeight(edge, bagCount);
    }

    private int countPaths() {
        Set<String> bagColors = graph.vertexSet();
        String target = "shiny gold";
        int targetPaths = 0;

        for (String bag : bagColors) {
            Iterator<String> iterator =  new DepthFirstIterator<>(graph, bag);
            while (iterator.hasNext()) {
                String nextBag = iterator.next();
                if (nextBag.equals(target)) {
                    targetPaths++;
                }
            }
        }
        return targetPaths;
    }

    private int countBags(String startVertex) {
        List<String> bagColors = Graphs.successorListOf(graph, startVertex);
        int bagCount = 0;
        if (bagColors.size() > 0) {
            for (String innerBag : bagColors) {
                DefaultWeightedEdge edge = graph.getEdge(startVertex, innerBag);
                double weight = graph.getEdgeWeight(edge);
                System.out.println(startVertex + ":" + innerBag + ":" + weight);
                bagCount +=  weight * countBags(innerBag);
                if (Graphs.successorListOf(graph, innerBag).size() != 0) {
                    bagCount += weight;
                }
            }
        } else {
            bagCount = 1;
        }
        System.out.println(bagCount);
        return bagCount;
    }

    private void testing() {
        List<String> bags = Graphs.successorListOf(graph, "shiny gold");
        System.out.println(bags);
    }
}
