package pe.com.ibm.rest.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.ibm.rest.main.bean.ConsultaRequest;
import pe.com.ibm.rest.main.bean.Empresa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( "/ibm/informacion/empresas" )
public class EmpresaController{


    @PostMapping( value = "/consulta", consumes = "application/json", produces = "application/json" )
    public ResponseEntity<?> consultarEmpresa( @RequestHeader( "X-IBM-Client-Id" )     String clientIdParam,
									           @RequestHeader( "X-IBM-Client-Secret" ) String clientSecretParam,
									           @RequestHeader( "CADENA_01" )           String cadena01Param,
									           @RequestHeader( "CADENA_02" )           String cadena02Param, 
									           @RequestBody ConsultaRequest objRequestParam ){  

        try {
            //HEADERs: 
            if( clientIdParam == null || clientSecretParam == null || cadena01Param == null || cadena02Param == null ){
                return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( Map.of( "ERROR", "Faltan cabeceras requeridas" ) );
            }

            //BODY: 
            if( objRequestParam.getCodigo() == null || objRequestParam.getDni() == null ){
                return ResponseEntity.status( HttpStatus.BAD_REQUEST).body( Map.of( "ERROR", "Debe enviar 'codigo' & 'dni' en el body" ) );
            }

            
            Empresa objEmpresa_01 = null;
            Empresa objEmpresa_02 = null;
            List<Empresa> listarEmpresas = new ArrayList<Empresa>();
            
            if( objRequestParam.getCodigo().equals( "EMP-001" ) ) {
                objEmpresa_01 = new Empresa(
	                        "20123456789",
	                        "IBM DEL PERU S.A.",
	                        "Av. Javier Prado Este 4200 - Lima",
	                        "(01) 123-4567",
	                        "SOCIEDAD ANONIMA",
	                        "ACTIVA"
                );
        
                listarEmpresas.add( objEmpresa_01 );  
            }
            else if( objRequestParam.getCodigo().equals( "EMP-002" ) ) {                
                     objEmpresa_02 = new Empresa(
		                        "21198756789",
		                        "ORACLE DEL PERU S.A.",
		                        "Av. Tomas Marzano 1050 - Surco",
		                        "(01) 444-4517",
		                        "SOCIEDAD ANONIMA",
		                        "ACTIVA"
                     );
                      
                     listarEmpresas.add( objEmpresa_02 ); 
            }
            else{
                 objEmpresa_01 = new Empresa(
                        "20123456789",
                        "IBM DEL PERU S.A.",
                        "Av. Javier Prado Este 4200 - Lima",
                        "(01) 123-4567",
                        "SOCIEDAD ANONIMA",
                        "ACTIVA"
                 );
                
                 objEmpresa_02 = new Empresa(
                        "21198756789",
                        "ORACLE DEL PERU S.A.",
                        "Av. Tomas Marzano 1050 - Surco",
                        "(01) 444-4517",
                        "SOCIEDAD ANONIMA",
                        "ACTIVA"
                 );
                 
                 listarEmpresas.add( objEmpresa_01 ); 
                 listarEmpresas.add( objEmpresa_02 ); 
            }
 
            //RESPONSE: 
            Map<String, Object> response = new HashMap<>();
            response.put( "headers", Map.of( "X-IBM-Client-Id",     clientIdParam,
						                     "X-IBM-Client-Secret", clientSecretParam,
						                     "CADENA_01",           cadena01Param,
						                     "CADENA_02",           cadena02Param
            ));
            
            response.put( "request",       objRequestParam );
            response.put( "listaEmpresas", listarEmpresas );

            return ResponseEntity.ok( response );
        } 
        catch( Exception e) {
               return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( Map.of( "ERROR", e.getMessage() ) );
        }
    }
 
 }

