import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author cokya
 */
public class myTableModel extends AbstractTableModel {
    
    private int satirSayisi;
    private int kolonSayisi;
    private ResultSet resultSet;
    private ArrayList veri=new ArrayList();

    
    public myTableModel(ResultSet resultSet) throws Exception
    {
        setResultSet(resultSet);
    }

    public void setResultSet(ResultSet resultSet) throws Exception {
        this.resultSet = resultSet;
        ResultSetMetaData metaData = resultSet.getMetaData();
        satirSayisi = 0;
        kolonSayisi = metaData.getColumnCount();
        while (resultSet.next()) {
            Object[] row = new Object[kolonSayisi];
            for (int j = 0; j < kolonSayisi; j++) {
                row[j] = resultSet.getObject(j + 1);
            }
            veri.add(row);
            satirSayisi++;
        }
    }

    //Tablodaki satÄ±r sayÄ±sÄ±nÄ± dÃ¶ndÃ¼ren metod
    public int getRowCount() {
        return satirSayisi;
    }

    //Tablodaki kolon sayÄ±sÄ±nÄ± dÃ¶ndÃ¼ren metod
    public int getColumnCount() {
        return kolonSayisi;
    }

    //Bir hÃ¼credeki deÄŸeri dÃ¶ndÃ¼ren metod
    public Object getValueAt(int rowIndex, int columnIndex) {
       Object[] row=(Object[]) veri.get(rowIndex);
       return row[columnIndex];
    }

    //index'i verilen kolonun adÄ±nÄ± dÃ¶ndÃ¼ren fonksiyon.
    @Override
    public String getColumnName(int columnIndex) {
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            return metaData.getColumnName(columnIndex + 1);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
