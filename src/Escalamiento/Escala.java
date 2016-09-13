
package Escalamiento;

import Variables.UtilValue;
import Variables.Value;
import java.util.LinkedList;

public class Escala {

    public LinkedList premnmx(LinkedList m,double rangomin,double rangomax ){
    LinkedList min= UtilValue.minimobser(m);
    LinkedList max=UtilValue.maximobser(m);
    LinkedList mx=new LinkedList();
//        System.out.println(min);
//        System.out.println(max);
        for(int x=0;x<m.size();x++){
            Value o=(Value) m.get(x);
        LinkedList obj=(LinkedList) o.getValue() ;
         LinkedList aux= new LinkedList();
         for(int y=0;y<obj.size();y++){
             double a=(rangomax-rangomin)*(Double.parseDouble((String) obj.get(y))-(double)(min.get(y)));
             double b=((double)(max.get(y))-(double)(min.get(y)));
             aux.add(""+((a/b)-Math.abs(rangomin)));
         }
        mx.add(new Value(o.getName(),aux));
        
        }
        //UtilValue.show(mx);
    return mx;
    }
    
}
