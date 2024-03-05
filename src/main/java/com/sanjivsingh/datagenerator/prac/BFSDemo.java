package com.sanjivsingh.datagenerator.prac;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Sanjiv.Singh
 * 
 */

public class BFSDemo {

	// define node here
	static class Node {

		int value;
		boolean visited = false; // optional
		List<Node> vertices = new ArrayList<Node>();

		public Node(int value) {
			super();
			this.value = value;
		}

		public void addVertex(Node n) {
			vertices.add(n);
		}

		@Override
		public String toString() {
			return "Node [value=" + value + ", visited=" + visited
					+ ", vertices=" + vertices + "]";
		}
	};

	public static Node find(Node root, int element) {
		// #1: Initialize queue (q)
		Queue<Node> q = new ConcurrentLinkedQueue<Node>(); // some queue
		// implementation
		// #2: Push root node to queue
		q.add(root);

		// #3: While queue not empty
		while (!q.isEmpty()) {

			// #:4 Dequeue n
			Node n = (Node) q.poll();
			// visit this node
			n.visited = true;

			// #5: If n == required_node, return n;
			if (n.value == element)
				return n;

			// #5: foreach vertices v of n
			for (Node v : n.vertices) {
				// #6: if v is visited, continue
				if (v.visited)
					continue;
				// #7: else enque v
				q.add(v);
			}
		}
		// #8: return null;
		return null; // cannot find element
	}

	public static void main(String[] args) {

		// create graph/tree
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);

		// call traverse
		n1.addVertex(n2);
		n1.addVertex(n3);

		n3.addVertex(n4);
		n3.addVertex(n5);

		Node found = find(n1, 5);
		System.out.println(found);
		// Node [value=4, visited=true, vertices=[]]

		found = find(n1, 4);
		System.out.println(found);
		// null
	}
}