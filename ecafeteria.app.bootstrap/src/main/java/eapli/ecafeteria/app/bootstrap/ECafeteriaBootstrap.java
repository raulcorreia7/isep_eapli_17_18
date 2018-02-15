package eapli.ecafeteria.app.bootstrap;

import eapli.ecafeteria.bootstrapers.ECafeteriaBootstraper;

/**
 * eCafeteria Bootstrapping data app
 *
 */
public class ECafeteriaBootstrap {

	public static void main(String[] args) {
		System.out.println("=====================================");
		System.out.println("Bootstrapping eCafeteria 2018 data");
		System.out.println("=====================================");

		new ECafeteriaBootstraper().execute();

		System.out.println("=====================================");
		System.out.println("Bootstrap data done.");
		System.out.println("=====================================");
	}
}
