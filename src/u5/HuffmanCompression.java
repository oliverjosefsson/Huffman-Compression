package u5;

import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCompression {
	private static int bytes; // The number of bytes used.

	public static HuffmanTree buildTree(int[] charFreqs) { 
		PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
		for (int i = 0; i < charFreqs.length; i++) {
			if (charFreqs[i] > 0) {
				trees.add(new HuffmanLeaf(charFreqs[i], (char) i));
			}
		}
		
		while (trees.size() > 1) {
			HuffmanTree a = trees.poll();
			HuffmanTree b = trees.poll();
			trees.add(new HuffmanNode(a, b)); 
		}
		return trees.poll();
	}
	
	public static void printResults(HuffmanTree tree) { 
		bytes = 0;
		System.out.println("Char\tFreq\tIn Huffman");
		printCodes(tree, new StringBuffer());
		bytes = (int) Math.ceil(bytes/8.0);
		System.out.println("Length of compressed text: " + bytes + " bytes");
	}

	private static void printCodes(HuffmanTree tree, StringBuffer prefix) { 
		if (tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf) tree;

			System.out.println((char)leaf.value + "\t" + leaf.frequency + "\t" + prefix);
			bytes += (leaf.frequency*prefix.length());

		} else if (tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode) tree;

			// Traverse down left in the tree 
			prefix.append('0');
			printCodes(node.left, prefix);
			prefix.deleteCharAt(prefix.length() - 1);

			// Traverse down right in the tree
			prefix.append('1');
			printCodes(node.right, prefix);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	public static void main(String[] args) {
		
		System.out.println("Enter the text:");
		Scanner scanner = new Scanner(System.in);
		String text = scanner.nextLine();
		
		int[] charFreqs = new int[128];
		for (char c : text.toCharArray()) {
			charFreqs[c]++;
		}

		HuffmanTree tree = buildTree(charFreqs);
		printResults(tree);
	}
}

