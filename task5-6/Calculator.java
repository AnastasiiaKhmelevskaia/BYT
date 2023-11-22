public class Calculator {
  public static void main(String[] args) {
    Handler addHandler = new AdditionHandler();
    Handler subtractHandler = new SubtractionHandler();
    Handler multiplyHandler = new MultiplicationHandler();
    Handler divideHandler = new DivisionHandler();

    addHandler.setNextHandler(subtractHandler);
    subtractHandler.setNextHandler(multiplyHandler);
    multiplyHandler.setNextHandler(divideHandler);

    Operation addOperation = new Operation(10, 5, "add");
    addHandler.calculate(addOperation);

    Operation subtractOperation = new Operation(10, 5, "subtract");
    addHandler.calculate(subtractOperation);

    Operation multiplyOperation = new Operation(10, 5, "multiply");
    addHandler.calculate(multiplyOperation);

    Operation divideOperation = new Operation(10, 5, "divide");
    addHandler.calculate(divideOperation);
  }
}

interface Handler {
  void setNextHandler(Handler handler);

  void calculate(Operation request);
}

class Operation {
  private int number1;
  private int number2;
  private String operation;

  public Operation(int number1, int number2, String operation) {
    this.number1 = number1;
    this.number2 = number2;
    this.operation = operation;
  }

  public int getNumber1() {
    return number1;
  }

  public int getNumber2() {
    return number2;
  }

  public String getOperation() {
    return operation;
  }
}

class AdditionHandler implements Handler {
  private Handler nextHandler;

  public void setNextHandler(Handler handler) {
    this.nextHandler = handler;
  }

  public void calculate(Operation request) {
    if ("add".equals(request.getOperation())) {
      System.out.println(request.getNumber1() + " + " + request.getNumber2() + " = " +
          (request.getNumber1() + request.getNumber2()));
    } else {
      nextHandler.calculate(request);
    }
  }
}

class SubtractionHandler implements Handler {
  private Handler nextHandler;

  public void setNextHandler(Handler handler) {
    this.nextHandler = handler;
  }

  public void calculate(Operation request) {
    if ("subtract".equals(request.getOperation())) {
      System.out.println(request.getNumber1() + " - " + request.getNumber2() + " = " +
          (request.getNumber1() - request.getNumber2()));
    } else {
      nextHandler.calculate(request);
    }
  }
}

class MultiplicationHandler implements Handler {
  private Handler nextHandler;

  public void setNextHandler(Handler handler) {
    this.nextHandler = handler;
  }

  public void calculate(Operation request) {
    if ("multiply".equals(request.getOperation())) {
      System.out.println(request.getNumber1() + " * " + request.getNumber2() + " = " +
          (request.getNumber1() * request.getNumber2()));
    } else {
      nextHandler.calculate(request);
    }
  }
}

class DivisionHandler implements Handler {
  private Handler nextHandler;

  public void setNextHandler(Handler handler) {
    this.nextHandler = handler;
  }

  public void calculate(Operation request) {
    if ("divide".equals(request.getOperation())) {
      System.out.println(request.getNumber1() + " / " + request.getNumber2() + " = " +
          (request.getNumber1() / request.getNumber2()));
    } else {
      nextHandler.calculate(request);
    }
  }
}
