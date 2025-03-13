package com.springboot.bookingcare.ServiceImplement;

import com.springboot.bookingcare.Service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

@Service
public class ImageServiceImplement implements ImageService {
    @Override
    public String upload(MultipartFile multipartFile,String applicationPath) throws FileNotFoundException {
        // tên file=tên file+thời gian hiện tại=> tránh replace hình ảnh
        String fileName=System.currentTimeMillis()+multipartFile.getOriginalFilename();
        //  tạo đường dẫn đến nơi lưu trữ file trên server
        String uploadFilePath=applicationPath+ File.separator+"uploads";
        // tạo đối tượng file với đường dẫn uploadFilePath
        File uploadDir=new File(uploadFilePath);
        // kiểm tra đường dẫn =>có thì thư mục tồn tại, chưa th tạo mới
        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }
        // tạo đối tượng file mới và truyền vào đối tượng này đường dẫn của tệp cần được lưu trữ =đừg dẫn thu mc+ tên file
        File fileImage=new File(uploadDir+File.separator+fileName);
        // tạo đối tượng fos để ghi dữ liệu vào file với đường dẫn được chỉ định(lúc này file vẫn chưa được tạo ra)
        try (FileOutputStream fos = new FileOutputStream(fileImage);
             //Đọc dữ liệu nhị phân từ body của part đưa vào biến fileContent
             InputStream fileContent = multipartFile.getInputStream()) {
            // tạo một mảng byte có kích thước 1024 để lưu trữ tạm thời dữ lệu trong file
            //buffer giúp xử lý dữ liệu từng phần, giảm thiểu việc ử dụng bộ nớ
            byte[] buffer = new byte[1024];
            //bytesRead để lưu trữ số byte thực tế đã được đọc trong mỗi vòng lặp.
            int bytesRead;
            //fileContent.read(buffer): Đọc tối đa 1024 bytes vào mảng buffer và trả về số byte thực tế đã đọc. Nếu không còn dữ liệu để đọc, nó sẽ trả về -1.
            // bytesRead la biến lưu trữ số file thật sự được đọc
            while ((bytesRead =fileContent.read(buffer)) != -1)
            {
                // sử dụng phương thức write trong FileOutputStream để ghi dữ liệu vào file
                // mảng chứa các byte đã được đọc, ghi từ vị trí nào, số byte sẽ ghi vào file
                fos.write(buffer, 0, bytesRead);
            }

        } catch (Exception e) {
            e.getMessage();
            return null;
        }
        return fileName;
    }
}
