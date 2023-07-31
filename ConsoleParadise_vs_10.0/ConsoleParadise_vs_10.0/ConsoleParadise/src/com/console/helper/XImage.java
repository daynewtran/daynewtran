package com.console.helper;


import com.console.entity.NhanVien;
import com.console.main.PanelSanPham;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class XImage {
    /*
     * Ảnh biểu tượng của ứng dụng, xuất hiện trên mọi cửa sổ
     */
    public static final Image APP_ICON;
    static{
        // Tải biểu tượng ứng dụng
        String file = "/com/console/icon/console.jpg";
        APP_ICON = new ImageIcon(XImage.class.getResource(file)).getImage();
    }
    
    /*
     * Sao chép file logo chuyên đề vào thư mục logo
     * @param file là đối tượng file ảnh
     * @return chép được hay không
     */   
    public static void saveLogo(File oldFile){
        
        File dir = new File("C:\\logos",oldFile.getName());
        // Tạo thư mục nếu chưa tồn tại
        if(!dir.getParentFile().exists()){
            dir.getParentFile().mkdirs();
        }
        
            try {
                Path source = Paths.get(oldFile.getAbsolutePath());
            Path destination = Paths.get("C:\\logos\\"+oldFile.getName());
                //System.out.println("logos\\"+oldFile.getName());
                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                
            } catch (IOException ex) {
                System.out.println("");
            }            
    }
    /*
     * Đọc hình ảnh logo chuyên đề
     * @param fileName  là tên file logo
     * @return ảnh đọc được
     */   
    public static ImageIcon readLogo(String fileName){
        File path = new File("C:\\logos", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
  
}