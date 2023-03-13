import java.lang.reflect.*;

public class Reflection {

    // takes a Class object and a String, and checks whether that class
    // has a field represented by the String, and returns a boolean
    static Boolean checkField (Class<?> clazz, String field) {
        if (clazz == null){
            return false;
        }
        try{
            Field checkClass = clazz.getDeclaredField(field);
            return true;
        } catch (NoSuchFieldException errorFieldException){
            try{
                Field checkAnsesters = clazz.getField(field);
                return true;
            } catch (NoSuchFieldException errorFieldException2){
            	return false;
            }
        }
    }

    static Boolean checkMethod (Class<?> clazz, String field){
        if (clazz == null) {
            return false;
        }
        try{
            Method checkClass = clazz.getDeclaredMethod(field);
            return true;
        } catch (NoSuchMethodException errorMethodException){
            try{
                Method checkAncestors = clazz.getMethod(field);
                return true;
            } catch (NoSuchMethodException errorMethodException2){
                return false;
            }
        }
    }

    static Boolean isClass(Class<?> clazz){
        if (clazz instanceof Class && !clazz.isInterface()){
            return true;
        }
        return false;
    }

    static Boolean isInterface(Class<?> clazz){
        return clazz.isInterface();
    }

    static Boolean hasAncestor (Class<?> clazz, String ancestor){
        if (clazz == null){
            return false;
        }
        // Checks classes
        Class<?> parent = clazz.getSuperclass();
        while (parent != null){
            if (parent.getName().equals(ancestor)){
                return true;
            }
            parent = parent.getSuperclass();
        }
        // Checks interfaces (Idea used from model Soln)
        for (Class<?> parentInterface : clazz.getInterfaces()){
            if (parentInterface.getName().equals(ancestor)){
                return true;
            }
        }
        return false;
    }

    String WishYouA(){
        return "Happy St. Patrick's Day!";
    }

    public static void main(String args[]) {
        System.out.println("checkField(C.class, 'f_A'): " + Reflection.checkField(C.class, "f_A"));
        System.out.println("checkMethod(C.class, 'm_X'): " + Reflection.checkMethod(C.class, "m_X"));
        System.out.println("isClass(C.class): " + Reflection.isClass(C.class));
        System.out.println("isClass(X.class): " + Reflection.isClass(X.class));
        System.out.println("isInterface(C.class): " + Reflection.isInterface(C.class));
        System.out.println("isInterface(X.class): " + Reflection.isInterface(X.class));
        System.out.println("hasAncestor(C.class, 'A'): " + Reflection.hasAncestor(C.class, "A"));
        System.out.println("hasAncestor(C.class, 'C'): " + Reflection.hasAncestor(C.class, "C"));
        System.out.println("hasAncestor(C.class, 'X'): " + Reflection.hasAncestor(C.class, "X"));
        System.out.println("hasAncestor(B.class, 'X'): " + Reflection.hasAncestor(B.class, "X"));
        System.out.println("hasAncestor(X.class, 'X'): " + Reflection.hasAncestor(X.class, "X"));
        System.out.println(new Reflection().WishYouA());
    }
}

// Test Cases //
class A {
    public String f_A;
}

class B extends A { }

interface X {
    void m_X();
}

class C extends B implements X {
    private String f_C;
    public void m_X() { }
}
