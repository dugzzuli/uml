/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mxgraph.mysqlUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

 
/**
 * POJO 工具生成类
 * @author  liangzz
 * 日期：2016-05-20
 */
public class GenEntity {
     
	private String package_url="com.mxgraph.model";//包的路径
	//类输出路径
	private String outputPath ="E:\\github\\uml\\jgraphx\\examples\\com\\mxgraph\\model" ;
								
    private String tablename = "yewu";//表名
    private String[] colnames; // 列名数组
    private String[] colTypes; //列名类型数组
    private String[] colDescs; //列名描述数组
    private int[] colSizes; //列名大小数组
    private boolean f_util = false; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*
     
    //数据库连接
     private static final String URL ="jdbc:mysql://localhost:3306/uml";
     private static final String NAME = "root";
     private static final String PASS = "123456";
     private static final String DRIVER ="com.mysql.jdbc.Driver";
 
    /*
     * 构造函数
     */
    public GenEntity(){
        //创建连接
        Connection con = null;
        //查要生成实体类的表
//        String sql = "select * from " + tablename;
//        PreparedStatement pStemt = null;
        try {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            con = DriverManager.getConnection(URL,NAME,PASS);
            DatabaseMetaData dbmd=con.getMetaData();
//            ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[] { "TABLE" });
//            while (resultSet.next()) {
//                String tableName=resultSet.getString("TABLE_NAME");
                //System.out.println(tableName);
//                if(tableName.equals(tablename)){
            //直接取表字段
                    ResultSet rs = dbmd.getColumns(null, "%", tablename, "%");
                    System.out.println("表名："+tablename+"\t\n表字段信息：");
                    rs.last();
                    int size = rs.getRow();
                    rs.beforeFirst();
                    colnames = new String[size];
                    colTypes = new String[size];
                    colSizes = new int[size];
                    colDescs = new String[size];
                    int i=0;
                    while(rs.next()){
                        System.out.println(rs.getString("COLUMN_NAME")+"----"+rs.getString("REMARKS")+"----"+rs.getString("TYPE_NAME"));
                            colnames[i] = initcapCol(rs.getString("COLUMN_NAME"));
                            colTypes[i] = rs.getString("TYPE_NAME");
                            colDescs[i] = rs.getString("REMARKS");
                            colSizes[i] = rs.getInt("COLUMN_SIZE");
                            if(colTypes[i].equalsIgnoreCase("datetime")){
                                f_util = true;
                            }
                            if(colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")){
                                f_sql = true;
                            }
                            i++;
                    }
//                }
//            }
           /* pStemt = con.prepareStatement(sql);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            ResultSet rs = rsmd.getColumns(null, "%", tablename, "%");
            int size = rsmd.getColumnCount();   //统计列
            colnames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            for (int i = 0; i < size; i++) {
                colnames[i] = initcapCol(rsmd.getColumnName(i + 1));
                colTypes[i] = rsmd.getColumnTypeName(i + 1);
                 
                if(colTypes[i].equalsIgnoreCase("datetime")){
                    f_util = true;
                }
                if(colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")){
                    f_sql = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
             */
            String content = parse(colnames,colTypes,colSizes);
             
            try {
                File directory = new File("");
//                String path=this.getClass().getResource("").getPath();
//                System.out.println(path);
//                System.out.println("src/?/"+path.substring(path.lastIndexOf("/com/", path.length())) );
                //String outputPath = directory.getAbsolutePath()+ "\\src\\main\\java\\"+this.package_url.replace(".", "\\")+"\\"+initcap(tablename) + ".java";  
                //D:\Workspaces\gzszjyc3_weixin\src\main\java\com\sjq\sys\pojo
                outputPath+="\\"+initcap(tablename) + ".java";
                FileWriter fw = new FileWriter(outputPath);
                System.out.println("输出路径："+outputPath);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(content);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
             
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
          try {
              con.close();
          } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
        }
    }
 
    /**
     * 功能：生成实体类主体代码
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
        StringBuffer sb = new StringBuffer();
         
        //判断是否导入工具包
        sb.append("package "+package_url+";\r\n");
        if(f_util){
            sb.append("import java.util.Date;\r\n");
        }
        if(f_sql){
            sb.append("import java.sql.*;\r\n");
        }
        sb.append("\r\n");
        //注释部分
        sb.append("   /**\r\n");
        sb.append("    * "+tablename+" 实体类\r\n");
        sb.append("    * @author liangzz\r\n");
//        sb.append("    * @date"+DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm")+" \r\n");
        sb.append("    */ \r\n");
        //实体部分
        sb.append("\r\npublic class " + initcap(tablename) + "{\r\n");
        processAllAttrs(sb);//属性
        processAllMethod(sb);//get set方法
        sb.append("}\r\n");
         
        //System.out.println(sb.toString());
        return sb.toString();
    }
     
    /**
     * 功能：生成所有属性
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {
         
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\t /**"+colDescs[i]+"**/\r\n");
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i],colSizes[i]) + " " + colnames[i] + ";\r\n");
        }
         
    }
 
    /**
     * 功能：生成所有方法
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {
         
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\r\n\tpublic void set" + initcap(colnames[i]) + "(" + sqlType2JavaType(colTypes[i],colSizes[i]) + " " + 
                    colnames[i] + "){\r\n");
            sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\r\n\tpublic " + sqlType2JavaType(colTypes[i],colSizes[i]) + " get" + initcap(colnames[i]) + "(){\r\n");
            sb.append("\t\treturn " + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
        }
         
    }
     
    /**
     * 功能：将输入字符串的首字母及下划线后的字母改成大写
     * @param str
     * @return
     */
    private String initcap(String str) {
         
        /*char[] ch = str.toCharArray();
        if(ch[0] >= 'a' && ch[0] <= 'z'){
            ch[0] = (char)(ch[0] - 32);
        }*/
       String[] arr= str.split("_");
       String tempStr ="";
       if(arr.length>0){
    	   for(String st:arr){
    		   char[] c = st.toCharArray();
    		   if(c[0] >= 'a' && c[0] <= 'z'){
    	            c[0] = (char)(c[0] - 32);
    	        }
    		   tempStr += new String(c);
    	   }
       }
         
        return tempStr;
    }
    
    /**
     * 功能：将输入字符串的下划线后的字母改成大写
     * @param str
     * @return
     */
    private String initcapCol(String str) {
       String[] arr= str.split("_");
       String tempStr ="";
       if(arr.length>1){
    	   int i=0;
    	   for(String st:arr){
    		   if(i>0){
    			   char[] c = st.toCharArray();
    			   if(c[0] >= 'a' && c[0] <= 'z'){
    				   c[0] = (char)(c[0] - 32);
    			   }
    			   tempStr += new String(c);
    		   }else{
    			   tempStr += st;
    		   }
    		   i++;
    	   }
       }else{
    	   tempStr = str; 
       }
         
        return tempStr;
    }
 
    /**
     * 功能：获得列的数据类型
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType,int typeSize) {
         
        if(sqlType.equalsIgnoreCase("bit")){
            return "Boolean";
        }else if(sqlType.equalsIgnoreCase("tinyint")){
            return "Byte";
        }else if(sqlType.equalsIgnoreCase("smallint")){
            return "Short";
        }else if(sqlType.equalsIgnoreCase("int")){
        	if(typeSize>=10){
        		return "Long";
        	}else{
        		return "Integer";
        	}
        }else if(sqlType.equalsIgnoreCase("bigint")){
            return "Long";
        }else if(sqlType.equalsIgnoreCase("float")){
            return "Float";
        }else if(sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") 
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money") 
                || sqlType.equalsIgnoreCase("smallmoney") || sqlType.equalsIgnoreCase("double")){
            return "Double";
        }else if(sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") 
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar") 
                || sqlType.equalsIgnoreCase("text")){
            return "String";
        }else if(sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("timestamp")){
            return "Date";
        }else if(sqlType.equalsIgnoreCase("image")){
            return "Blod";
        }else if(sqlType.equalsIgnoreCase("bigint")){
        	return "BigInteger";
        }
         
        return null;
    }
     
    /**
     * 出口
     * TODO
     * @param args
     */
    public static void main(String[] args) {
         
        new GenEntity();
//    	this.initcap("admin_test_as");
    }
 
}