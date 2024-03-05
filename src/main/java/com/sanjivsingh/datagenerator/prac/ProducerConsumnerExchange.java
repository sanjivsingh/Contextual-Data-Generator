package com.sanjivsingh.datagenerator.prac;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

import com.sanjivsingh.datagenerator.core.model.DataBuffer;

public class ProducerConsumnerExchange {
	
	Exchanger<DataBuffer> exchanger = new Exchanger<DataBuffer>();
	DataBuffer initialEmptyBuffer = new DataBuffer();
	DataBuffer initialFullBuffer = new DataBuffer();

	class FillingLoop implements Runnable {

		private int index = 0;
		private int iteration = 0;

		public FillingLoop(int index) {
			super();
			this.index = index;
		}

		public void run() {
			DataBuffer currentBuffer = initialEmptyBuffer;
			try {
				while (currentBuffer != null) {
					addToBuffer(currentBuffer);
					if (currentBuffer.isFull())
						currentBuffer = exchanger.exchange(currentBuffer);

				}
			} catch (InterruptedException ex) {
			}
		}

		private void addToBuffer(DataBuffer currentBuffer) {

			if (currentBuffer.isEmpty()) {
				iteration++;
				List<String> records = new ArrayList<String>();
				records.add(index + "-aa-" + iteration);
				records.add(index + "-bb-" + iteration);
				currentBuffer.setRecords(records);
				System.out.println("Put[" + index + "] : "
						+ currentBuffer.getRecords());
				try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	class EmptyingLoop implements Runnable {

		private int index = 0;

		public EmptyingLoop(int index) {
			super();
			this.index = index;
		}

		public void run() {
			DataBuffer currentBuffer = initialFullBuffer;
			try {
				while (currentBuffer != null) {
					takeFromBuffer(currentBuffer);

					if (currentBuffer.isEmpty())
						currentBuffer = exchanger.exchange(currentBuffer);

				}
			} catch (InterruptedException ex) {
			}
		}

		private void takeFromBuffer(DataBuffer currentBuffer) {
			System.out.println("Get[" + index + "] : "
					+ currentBuffer.getRecords());
			currentBuffer.setRecords(new ArrayList<String>());

			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void start() {
		new Thread(new FillingLoop(0)).start();
		new Thread(new EmptyingLoop(0)).start();
	}

	public static void main(String[] args) {
		ProducerConsumnerExchange bb = new ProducerConsumnerExchange();
		bb.start();
	}

}
