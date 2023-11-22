public class StatePattern {
  public static void main(String[] args) {
    VendingMachine vendingMachine = new VendingMachine();
    vendingMachine.selectProduct("Coke");
    vendingMachine.insertMoney(0);
    vendingMachine.dispenseProduct();
  }
}

interface VendingMachineState {
  void selectProduct(VendingMachine context, String product);

  void insertMoney(VendingMachine context, double amount);

  void dispenseProduct(VendingMachine context);
}

class IdleState implements VendingMachineState {
  @Override
  public void selectProduct(VendingMachine context, String product) {
    System.out.println("Product " + product + " selected.");
    context.setState(context.getSelectionState());
  }

  @Override
  public void insertMoney(VendingMachine context, double amount) {
    System.out.println("No product selected. Please select a product first.");
  }

  @Override
  public void dispenseProduct(VendingMachine context) {
    System.out.println("No product selected.");
  }
}

class SelectionState implements VendingMachineState {
  public void selectProduct(VendingMachine context, String product) {
    System.out.println("Product " + product + " already selected. Insert money to proceed.");
  }

  public void insertMoney(VendingMachine context, double amount) {
    if (amount <= 0) {
      System.out.println("No money inserted. Please insert money to purchase the product.");
    } else {
      System.out.println("Money inserted: " + amount);
      context.setState(context.getDispensingState());
    }
  }

  public void dispenseProduct(VendingMachine context) {
    System.out.println("Please insert money first.");
  }
}

class DispensingState implements VendingMachineState {
  public void selectProduct(VendingMachine context, String product) {
    System.out.println("Please wait, dispensing another product.");
  }

  public void insertMoney(VendingMachine context, double amount) {
    System.out.println("Please wait, currently dispensing a product.");
  }

  public void dispenseProduct(VendingMachine context) {
    System.out.println("Dispensing product. Thank you!");
    context.setState(context.getIdleState());
  }
}

class VendingMachine {
  private final VendingMachineState idleState = new IdleState();
  private final VendingMachineState selectionState = new SelectionState();
  private final VendingMachineState dispensingState = new DispensingState();
  private VendingMachineState state;

  public VendingMachine() {
    state = idleState;
  }

  public void setState(VendingMachineState state) {
    this.state = state;
  }

  public void selectProduct(String product) {
    state.selectProduct(this, product);
  }

  public void insertMoney(double amount) {
    state.insertMoney(this, amount);
  }

  public void dispenseProduct() {
    state.dispenseProduct(this);
  }

  public VendingMachineState getIdleState() {
    return idleState;
  }

  public VendingMachineState getSelectionState() {
    return selectionState;
  }

  public VendingMachineState getDispensingState() {
    return dispensingState;
  }
}
