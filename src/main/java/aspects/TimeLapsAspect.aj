package aspects;

import interfaz.InterfazPrincipal;

public aspect TimeLapsAspect {
	
	pointcut javaRegx() : call(void InterfazPrincipal.limpiar());
	after(): javaRegx() {
		System.out.println("[ASPECT AROUND] Termino la ejecucion!");
	}
	
}
