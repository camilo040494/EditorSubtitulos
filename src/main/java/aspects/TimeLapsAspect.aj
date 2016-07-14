package aspects;

import interfaz.InterfazPrincipal;

public aspect TimeLapsAspect {
	
	pointcut javaRegx() : call(void InterfazPrincipal.limpiar());
	Object around(): javaRegx() {
		long time = System.currentTimeMillis();
		proceed();
		long tiempo = System.currentTimeMillis()-time;
		System.out.println("[ASPECT AROUND] el tiempo total es: "+tiempo);
		return tiempo;
	}
	
}
