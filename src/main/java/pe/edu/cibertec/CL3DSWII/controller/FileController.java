package pe.edu.cibertec.CL3DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @PostMapping("/filesimages")
    public ResponseEntity<ResponseFile> uploadImageFiles(@RequestParam("files") List<MultipartFile> files) {
        for (MultipartFile file : files) {
            if (!file.getOriginalFilename().toLowerCase().endsWith(".png")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ResponseFile.builder().message("Solo se permiten archivos con extensión PNG en /filesimages").build());
            }
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseFile.builder().message("Los archivos de imágenes fueron cargados correctamente").build());
    }

    @PostMapping("/filesexcel")
    public ResponseEntity<ResponseFile> uploadExcelFiles(@RequestParam("files") List<MultipartFile> files) {
        // Validar que la extensión del archivo sea XLSX y el tamaño máximo del archivo sea 1.5MB
        for (MultipartFile file : files) {
            if (!file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ResponseFile.builder().message("Solo se permiten archivos con extensión XLSX en /filesexcel").build());
            }
            if (file.getSize() > 1.5 * 1024 * 1024) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ResponseFile.builder().message("El tamaño máximo del archivo es 1.5MB en /filesexcel").build());
            }
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseFile.builder().message("Los archivos de Excel fueron cargados correctamente").build());
    }

}
