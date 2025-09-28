
class Employee {

    private int id;
    private String name;
    private String designation;
    private double salary;
    private String department;
    private String email;
    private String phone;

    public Employee(int id, String name, String designation, double salary, String department, String email,
                    String phone) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
        this.department = department;
        this.email = email;
        this.phone = phone;
    }
    public int getId() { return id; }

public String getName() { return name; }

public String getDesignation() { return designation; }

public double getSalary() { return salary; }

public String getDepartment() { return department; }

public String getEmail() { return email; }

public String getPhone() { return phone; }

public void setName(String name) { this.name = name; }

public void setDesignation(String designation) { this.designation = designation; }

public void setSalary(double salary) { this.salary = salary; }

public void setDepartment(String department) { this.department = department; }

public void setEmail(String email) { this.email = email; }

public void setPhone(String phone) { this.phone = phone; }

public String toFileString() {
    return String.format("%d,%s,%s,%.2f,%s,%s,%s", 
        id, escape(name), escape(designation), salary, escape(department), escape(email), escape(phone));
}
public static Employee fromFileString(String line) { 

    String[] parts = splitCSV(line);

    if (parts.length < 7) return null;

    try {
        int id = Integer.parseInt(parts[0]);
        String name = unescape(parts[1]);
        String designation = unescape(parts[2]);
        double salary = Double.parseDouble(parts[3]);
        String department = unescape(parts[4]);
        String email = unescape(parts[5]);
        String phone = unescape(parts[6]);

        return new Employee(id, name, designation, salary, department, email, phone);

    } catch (Exception e) {
        return null;
    }
}

public String display() {
    return String.format(
        "ID: %d | Name: %s | Designation: %s | Salary: %.2f | Dept: %s | Email: %s | Phone: %s",
        id, name, designation, salary, department, email, phone
    );
}

private static String escape(String s) {
    if (s == null) return "";
    return s.replace(",", "\\,");
}
private static String unescape(String s) {
    if (s == null) return "";
    return s.replace("\\,", ",");
}

private static String[] splitCSV(String line) {
    List<String> parts = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    boolean escape = false;

    for (int i = 0; i < line.length(); i++) {
        char c = line.charAt(i);
        if (escape) {
            sb.append(c);
            escape = false;
        } else {
            if (c == '\\') escape = true;
            else if (c == ',') {
                parts.add(sb.toString());
                sb = new StringBuilder();
            } else sb.append(c);
        }
    }

    parts.add(sb.toString());
    return parts.toArray(new String[0]);
}
class User {

    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public String toFileString() {
        return username + "," + password + "," + role;
    }

    public static User fromFileString(String line) {
        String[] p = line.split(",", -1);
        if (p.length < 3) return null;
        return new User(p[0], p[1], p[2]);
    }
}
class Department {

    private String name;
    private String manager;

    public Department(String name, String manager) {
        this.name = name;
        this.manager = manager;
    }

    public String getName() { return name; }
    public String getManager() { return manager; }

    public void setManager(String manager) { this.manager = manager; }

    public String toFileString() {
        return escape(name) + "," + escape(manager);
    }

    public static Department fromFileString(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length < 2) return null;
        return new Department(unescape(parts[0]), unescape(parts[1]));
    }

    private static String escape(String s) { return s.replace(",", "\\,"); }
    private static String unescape(String s) { return s.replace("\\,", ","); }
}

