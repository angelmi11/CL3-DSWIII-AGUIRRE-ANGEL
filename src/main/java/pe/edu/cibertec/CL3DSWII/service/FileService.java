package pe.edu.cibertec.CL3DSWII.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileService {

    private final Path rootFolder = Paths.get("uploads");
    private final Path imagesFolder = Paths.get("uploads/images");
    private final Path excelFolder = Paths.get("uploads/excel");
    public void save(MultipartFile file) throws Exception {
        Files.copy(file.getInputStream(), this.rootFolder.resolve(file.getOriginalFilename()));
    }


    public void save(List<MultipartFile> files) throws Exception {
        for (MultipartFile file : files) {
            this.save(file);
        }
    }


    public void saveImage(MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();
        if(filename.endsWith(".png")) {
            Files.copy(file.getInputStream(), this.imagesFolder.resolve(filename));
        } else {
            throw new Exception("El archivo no es una imagen PNG");
        }
    }

    public void saveExcel(MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();
        if(filename.endsWith(".xlsx") && file.getSize() <= 1500000) {
            Files.copy(file.getInputStream(), this.excelFolder.resolve(filename));
        } else {
            throw new Exception("El archivo no es un Excel XLSX o es mayor a 1.5MB");
        }
    }

}
