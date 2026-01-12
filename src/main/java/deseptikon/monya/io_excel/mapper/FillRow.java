package deseptikon.monya.io_excel.mapper;

import deseptikon.monya.spring_jdbc_parcels.model.Parcel;
import org.dhatim.fastexcel.Worksheet;

public interface FillRow {
     default void parcelsFull(Worksheet ws, int row, Parcel parcel){
         ws.value(row,0, parcel.getCadastralNumber());
         ws.value(row,1, parcel.getArea());
         ws.value(row,2, parcel.getCategory());
         ws.value(row,3, parcel.getLocality());
         ws.value(row,4, parcel.getNote());
         ws.value(row,5, parcel.getUtilizationByDoc());
         ws.value(row,6, parcel.getInnerCadastralNumbers());
         ws.value(row,7, parcel.getUsageCode());
         ws.value(row,8, parcel.getPredictedUsageCode());

     }
}
