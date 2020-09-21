package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread {

	private int cod;
	private double saldo;
	private double valor;
	public int tipo;
	private static int tipoSaque = 0;
	private static int tipoDeposito = 0;
	Semaphore semaforo = new Semaphore(2);

	public ThreadBanco(int cod, double saldo, double valor, int tipo) {

		this.cod = cod;
		this.saldo = saldo;
		this.valor = valor;
		this.tipo = tipo;
	}

	public void run() {
		
		Random numero = new Random();
		try {
			sleep((numero.nextInt(999) + 1));
			sleep((numero.nextInt(999) + 1));
			sleep((numero.nextInt(999) + 1));
			sleep((numero.nextInt(999) + 1));
			if (tipo == 1 & tipoSaque == 0) {
				semaforo.acquire();
				tipoSaque = 1;
				OperaçãoSac();
				tipoSaque = 0;
				semaforo.release();
			} else if (tipo == 1 & tipoSaque == 1) {
				sleep((numero.nextInt(999) + 1));
				sleep((numero.nextInt(999) + 1));
				sleep((numero.nextInt(999) + 1));
				if (tipo == 1 & tipoSaque == 0) {
					semaforo.acquire();
					tipoSaque = 1;
					OperaçãoSac();
					tipoSaque = 0;
					semaforo.release();
				} else {
					sleep((numero.nextInt(999) + 1));
					sleep((numero.nextInt(999) + 1));
					sleep((numero.nextInt(999) + 1));
					semaforo.acquire();
					tipoSaque = 1;
					OperaçãoSac();
					tipoSaque = 0;
					semaforo.release();
				}
			} else if (tipo == 2 & tipoDeposito == 0) {
				semaforo.acquire();
				tipoDeposito = 1;
				OperaçãoDep();
				tipoDeposito = 0;
				semaforo.release();
			} else if (tipo == 2 & tipoDeposito == 1) {
				sleep((numero.nextInt(999) + 1));
				sleep((numero.nextInt(999) + 1));
				sleep((numero.nextInt(999) + 1));
				if (tipo == 2 & tipoDeposito == 0) {
					semaforo.acquire();
					tipoDeposito = 1;
					OperaçãoDep();
					tipoDeposito = 0;
					semaforo.release();
				} else {
					sleep((numero.nextInt(999) + 1));
					sleep((numero.nextInt(999) + 1));
					sleep((numero.nextInt(999) + 1));
					semaforo.acquire();
					tipoDeposito = 1;
					OperaçãoDep();
					tipoDeposito = 0;
					semaforo.release();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public void OperaçãoSac () {
		
		System.out.println("");
		System.out.println("A conta " + cod + " tinha: " + saldo + " R$");
		saldo -= valor;
		System.out.println("Valor sacado foi de: " + valor + " R$");
		System.out.println("Valor restante na conta " + cod + " é de: " + saldo + " R$");
		System.out.println("");
	}
	
	public void OperaçãoDep () {
		
		System.out.println("");
		System.out.println("A conta " + cod + " tinha: " + saldo + " R$");
		saldo += valor;
		System.out.println("Valor depositado foi de: " + valor + " R$");
		System.out.println("Valor na conta " + cod + " agora é de: " + saldo + " R$");
		System.out.println("");
	}
}
