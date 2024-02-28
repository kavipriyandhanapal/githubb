



It is a Specialization of @Component which can be used for the classes, that declare one or more @ExceptionHandler methods.The classes which are annotated with controller advice can be considered as SpringBeans and these classes are also eligible for classpath scanning.

ResponseEntityExceptionHandler:
It is an abstract class present in spring web MVC framework.
It is a class which will have the exception handler methods, that will handle all the spring mvc raised exceptions by returning response entity.
It is a convinent base class of @Controller Advice, which is used to handle the exceptions in an applications.

@ExceptionHandler
It is a method level annotation belongs to Spring web mvc framework.
It is used to mark the method as the ExceptionHandler for the handler classes or methods in the exception class.
These  methods are allowed to have a Flexible method Signature.

Example program to understand@ControllerAdvice , @ExceptionHandler and ResponseEntityExceptionHandler.
public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(long phone, String password){
	Optional<Merchant> recMerchant=merchantDao.verify(phone,password);
	ResponseStructure<Merchant> structure=new ResponseStructure<>();
	if(recMerchant.isPresent()){
		structure.setMessage("Verification Successfull");
		structure.setData(recMerchant.get());
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
	}
	throw new InvalidCredentialsException("Invalid phone number or password");
}       


package org.jsp.merchantbootapp.exception;
public class InvalidCredentialsException extends RuntimeException{
	public InvalidCredentialsException(String message){
		super(message);
	}
}

MerchantBootAppExceptionHandler.java
@ControllerAdvice
public class MerchantBootAppExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ResponseStructure<String>> handleICE(InvalidCredentialsException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setData("cannot Find Merchant");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
}


package org.jsp.merchantbootapp.exception;
public class IdNotFoundException extends RuntimeException{
	@Override
	public String getMessage(){
		return "Invalid Id";
	}
}






	 