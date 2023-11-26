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


    public void saveImages(List<MultipartFile> files) throws Exception {
        for (MultipartFile file : files) {
            if (!file.getOriginalFilename().toLowerCase().endsWith(".png")) {
                throw new IllegalArgumentException("Solo se permiten archivos con extensión PNG en /filesimages");
            }
        }
    }

    public void saveExcel(List<MultipartFile> files) throws Exception {
        for (MultipartFile file : files) {
            if (!file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
                throw new IllegalArgumentException("Solo se permiten archivos con extensión XLSX en /filesexcel");
            }
            if (file.getSize() > 1.5 * 1024 * 1024) {
                throw new IllegalArgumentException("El tamaño máximo del archivo es 1.5MB en /filesexcel");
            }

        }
    }

}
