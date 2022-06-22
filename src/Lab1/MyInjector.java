package Lab1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@MyBean
public class MyInjector {
	@MyAutowired
	private Map<Class, Object> container = new HashMap<>();

	public Set<Class> findAllClassesUsingClassLoader(String name) {
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(name);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		return reader.lines().filter(line -> line.endsWith(".class")).map(line -> getClass(line, name))
				.collect(Collectors.toSet());
	}

	private Class getClass(String className, String packageName) {
		try {
			return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
		} catch (ClassNotFoundException e) {
			// handle the exception
		}
		return null;
	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		MyInjector s = new MyInjector();
		String myclass = s.getClass().getPackageName();
		System.out.println(myclass);
		// System.out.println(s.findAllClassesUsingClassLoader(myclass));

		for (Class c : s.findAllClassesUsingClassLoader(myclass)) {
			System.out.println(c.getSimpleName());

			//		Class<?> m = Class.forName(myclass +"."+c.getSimpleName());
			//		Constructor<?> ctor  =  m.getConstructor(String.class);
			//		Object object = ctor.newInstance("Test");
			//		System.out.println(object);
			// System.out.println("HERE
			// "+c.getClass().isAnnotationPresent(MyAutowired.class));

		}
	}

	public void scanForBeans() {
		// search
		// put to map
	}

	public void scanForAutowired() {
		// search
		// put to map
	}

}
