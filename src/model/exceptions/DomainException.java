package model.exceptions;

/*
RuntimeException - compilador não obriga a tratar o erro
Exception - compilador obriga o tratamento do erro
 */
public class DomainException extends RuntimeException {

  public DomainException(String msg) {
    super(msg);
  }
}
