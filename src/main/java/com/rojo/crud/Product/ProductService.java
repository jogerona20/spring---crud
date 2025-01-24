package com.rojo.crud.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    HashMap<String, Object> datos;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return this.productRepository.findAll();
    }

    public ResponseEntity<Object> newProduct(Product product) {
        Optional<Product> res = productRepository.findProductByName(product.getName());
        datos = new HashMap<>();

        if(res.isPresent() && product.getId()==null){
            datos.put("error", true);
            datos.put("message", "Ya existe el nombre del proucto");
           return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardo exitosamente");
        if(product.getId()!=null){
            datos.put("message", "Se actualizo con exito");
        }
        productRepository.save(product);
        datos.put("data", product);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> actualizarProducto(Product product) {
        datos = new HashMap<>();
        boolean existeId = this.productRepository.existsById(product.getId());

        if(product.getId()==null){
            datos.put("error", true);
            datos.put("message", "El id es obligatorio");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }

        if(!existeId){
            datos.put("error", true);
            datos.put("message", "El id existe");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }

        datos.put("message", "Se actualizo con exito");
        productRepository.save(product);
        datos.put("data", product);

        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deleteProudct(Long id){
        datos = new HashMap<>();
        boolean existeId = this.productRepository.existsById(id);

        if(!existeId){
            datos.put("error", true);
            datos.put("message", "El id del producto no existe");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        productRepository.deleteById(id);
        datos.put("message", "Producto eliminado");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
