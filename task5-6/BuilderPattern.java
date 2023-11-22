public class BuilderPattern {
  public static void main(String[] args) {
    ComputerDirector gamingDirector = new ComputerDirector(new GamingComputerBuilder());
    Computer gamingComputer = gamingDirector.assembleGamingComputer();
    System.out.println("Gaming Computer: " + gamingComputer.toString());

    ComputerDirector officeDirector = new ComputerDirector(new OfficeComputerBuilder());
    Computer officeComputer = officeDirector.assembleOfficeComputer();
    System.out.println("Office Computer: " + officeComputer.toString());
  }
}

class Computer {
  private String CPU;
  private String GPU;
  private String RAM;
  private String storage;

  public Computer(String CPU, String GPU, String RAM, String storage) {
    this.CPU = CPU;
    this.GPU = GPU;
    this.RAM = RAM;
    this.storage = storage;
  }

  public String getCPU() {
    return CPU;
  }

  public void setCPU(String cPU) {
    CPU = cPU;
  }

  public String getGPU() {
    return GPU;
  }

  public void setGPU(String gPU) {
    GPU = gPU;
  }

  public String getRAM() {
    return RAM;
  }

  public void setRAM(String rAM) {
    RAM = rAM;
  }

  public String getStorage() {
    return storage;
  }

  public void setStorage(String storage) {
    this.storage = storage;
  }

  public String toString() {
    return "Computer{" +
        "CPU='" + CPU + '\'' +
        ", GPU='" + GPU + '\'' +
        ", RAM='" + RAM + '\'' +
        ", storage='" + storage + '\'' +
        '}';
  }
}

interface ComputerBuilder {
  ComputerBuilder setCPU(String CPU);

  ComputerBuilder setGPU(String GPU);

  ComputerBuilder setRAM(String RAM);

  ComputerBuilder setStorage(String storage);

  Computer build();
}

class GamingComputerBuilder implements ComputerBuilder {
  private String CPU;
  private String GPU;
  private String RAM;
  private String storage;

  public ComputerBuilder setCPU(String CPU) {
    this.CPU = CPU;
    return this;
  }

  public ComputerBuilder setGPU(String GPU) {
    this.GPU = GPU;
    return this;
  }

  public ComputerBuilder setRAM(String RAM) {
    this.RAM = RAM;
    return this;
  }

  public ComputerBuilder setStorage(String storage) {
    this.storage = storage;
    return this;
  }

  public Computer build() {
    return new Computer(CPU, GPU, RAM, storage);
  }
}

class OfficeComputerBuilder implements ComputerBuilder {
  private String CPU;
  private String GPU;
  private String RAM;
  private String storage;

  public ComputerBuilder setCPU(String CPU) {
    this.CPU = CPU;
    return this;
  }

  public ComputerBuilder setGPU(String GPU) {
    this.GPU = GPU;
    return this;
  }

  public ComputerBuilder setRAM(String RAM) {
    this.RAM = RAM;
    return this;
  }

  public ComputerBuilder setStorage(String storage) {
    this.storage = storage;
    return this;
  }

  public Computer build() {
    return new Computer(CPU, GPU, RAM, storage);
  }
}

class ComputerDirector {
  private ComputerBuilder builder;

  public ComputerDirector(ComputerBuilder builder) {
    this.builder = builder;
  }

  public Computer assembleGamingComputer() {
    return builder.setCPU("High-end CPU")
        .setGPU("High-end GPU")
        .setRAM("32GB")
        .setStorage("1TB SSD")
        .build();
  }

  public Computer assembleOfficeComputer() {
    return builder.setCPU("Standard CPU")
        .setGPU("Integrated GPU")
        .setRAM("8GB")
        .setStorage("500GB HDD")
        .build();
  }
}