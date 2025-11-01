package pe.com.ibm.rest.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.ibm.rest.main.bean.ConsultaRequest;
import pe.com.ibm.rest.main.bean.Empresa;
import org.springframework.http.*; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( "/ibm/informacion/empresas" )
public class EmpresaController{

	@PostMapping( value = "/consulta", consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> consultarEmpresa(
		        @RequestHeader( "X-IBM-Client-Id" )     String clientIdParam,
		        @RequestHeader( "X-IBM-Client-Secret" ) String clientSecretParam,
		        @RequestHeader( "CADENA_01" )           String cadena01Param,
		        @RequestHeader( "CADENA_02" )           String cadena02Param,
		        @RequestBody( required = false ) ConsultaRequest objRequestParam ){

	    try{
	        //VALIDAR HEADERS
	        if( clientIdParam == null || clientSecretParam == null || cadena01Param == null || cadena02Param == null ){
	            return ResponseEntity.status( HttpStatus.BAD_REQUEST).body( Map.of( "ERROR", "Faltan HEADERs requeridas" ));
	        }

	        //VALIDAR BODY
	        if( objRequestParam.getCodigo() == null || objRequestParam.getDni() == null ){
	            return ResponseEntity.status( HttpStatus.BAD_REQUEST).body( Map.of( "ERROR", "Debe enviar 'CODIGO' & 'DNI' en el BODY" ) );
	        }

	        // ARMAR LISTA DE EMPRESAS
	        List<Empresa> listarEmpresas = new ArrayList<>();
	        if( "EMP-001".equals( objRequestParam.getCodigo() ) ){
	            listarEmpresas.add( new Empresa(
	                    "20123456789",
	                    "IBM DEL PERU S.A.",
	                    "Av. Javier Prado Este 4200 - Lima",
	                    "(01) 123-4567",
	                    "SOCIEDAD ANONIMA",
	                    "ACTIVA"
	            ) );
	        }
	        else if( "EMP-002".equals( objRequestParam.getCodigo() ) ){
		             listarEmpresas.add( new Empresa(
		                    "21198756789",
		                    "ORACLE DEL PERU S.A.",
		                    "Av. Tomas Marzano 1050 - Surco",
		                    "(01) 444-4517",
		                    "SOCIEDAD ANONIMA",
		                    "ACTIVA"
		             ) );
	        } 
	        else{
	             listarEmpresas.add( new Empresa(
	                    "20123456789",
	                    "IBM DEL PERU S.A.",
	                    "Av. Javier Prado Este 4200 - Lima",
	                    "(01) 123-4567",
	                    "SOCIEDAD ANONIMA",
	                    "ACTIVA"
	             ) );
	             listarEmpresas.add( new Empresa(
	                    "21198756789",
	                    "ORACLE DEL PERU S.A.",
	                    "Av. Tomas Marzano 1050 - Surco",
	                    "(01) 444-4517",
	                    "SOCIEDAD ANONIMA",
	                    "ACTIVA"
	             ) );
	        }

	        //CREAR BODY DE RESPONSE: 
	        Map<String, Object> responseBody = new HashMap<>();
	        responseBody.put( "request",       objRequestParam );
	        responseBody.put( "listaEmpresas", listarEmpresas  );

	        //CREAR HEADERS HTTP DE RESPONSE: 
	        HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.add( "X-IBM-Client-Id",     clientIdParam     + "- [PROCESADO]" );
	        responseHeaders.add( "X-IBM-Client-Secret", clientSecretParam + "- [PROCESADO]" );
	        responseHeaders.add( "CADENA_01",           cadena01Param     + "- [PROCESADO]" );
	        responseHeaders.add( "CADENA_02",           cadena02Param     + "- [PROCESADO]" );

	        //RETORNAR CON HEADERS: 
	        return new ResponseEntity<>( responseBody, responseHeaders, HttpStatus.OK ); 
        } 
        catch( Exception e) {
               return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( Map.of( "ERROR", e.getMessage() ) );
        }
    }
 
 }

