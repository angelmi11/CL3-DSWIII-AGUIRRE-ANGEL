package pe.edu.cibertec.CL3DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.CL3DSWII.model.response.ResponseFile;
import pe.edu.cibertec.CL3DSWII.service.FileService;


import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/files")
public class FileController {

    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseFile> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws Exception {
        fileService.save(files);
        return ResponseEntity.status(HttpStatus.OK)
                .body( ResponseFile.builder().message("Los archivos fueron cargados correctamente al servidor").build());
    }

    @PreAuthorize("hasRole('GESTOR')")
    @PostMapping("/images")
    public ResponseEntity<ResponseFile> uploadImages(@RequestParam("files") List<MultipartFile> files) throws Exception {
        for (MultipartFile file : files) {
            fileService.saveImage(file);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body( ResponseFile.builder().message("Las imágenes fueron cargadas correctamente al servidor").build());
    }

    @PreAuthorize("hasRole('COORDINADOR')")
    @PostMapping("/excel")
    public ResponseEntity<ResponseFile> uploadExcelFiles(@RequestParam("files") List<MultipartFile> files) throws Exception {
        for (MultipartFile file : files) {
            fileService.saveExcel(file);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body( ResponseFile.builder().message("Los archivos Excel fueron cargados correctamente al servidor").build());
    }

}
