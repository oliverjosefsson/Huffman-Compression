package u5;

abstract class HuffmanTree implements Comparable<HuffmanTree> {
	public final int frequency; // The total frequency of this tree's leafs.

	public HuffmanTree(int freq) {
		frequency = freq;
	}

	public int compareTo(HuffmanTree tree) {
		return frequency - tree.frequency;
	}
}

class HuffmanLeaf extends HuffmanTree {
	public final char value; // The character the leaf represents

	public HuffmanLeaf(int freq, char val) {
		super(freq);
		value = val;
	}
}

class HuffmanNode extends HuffmanTree {
	public final HuffmanTree left, right; // Children of the tree node.

	public HuffmanNode(HuffmanTree l, HuffmanTree r) {
		super(l.frequency + r.frequency);
		left = l;
		right = r;
	}
}