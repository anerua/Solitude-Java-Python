/*
 * @author: Martins Anerua.
 * Martins Anerua © 2018-2019.
 * All rights reserved.
 */
package solitude;

import java.io.InputStream;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * 31-12-2018
 * @author MARTINS
 */
public class Logic {
    
    InputStream file = Logic.class.getResourceAsStream("/solitude/Logic.py");
    
    public void exec(String code, PythonInterpreter interp){
        interp.exec(code);
    }
    
    public void execfile(InputStream file, PythonInterpreter interp){
        interp.execfile(file);
    }
    
    public PyObject eval(String code, PythonInterpreter interp){
        return interp.eval(code);
    }
}
