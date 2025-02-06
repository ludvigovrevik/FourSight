# Navigate to the project directory
cd "$(dirname "$0")"

# Set JavaFX SDK path for macOS
JAVAFX_PATH="./javafx-sdk-21/mac/javafx-sdk-21.0.6/lib"

# Compile Java files with JavaFX and include resources
mkdir -p target
javac --module-path "$JAVAFX_PATH" --add-modules javafx.controls,javafx.fxml -d target -cp src/main/java:src/main/resources src/main/java/project/*.java

# Copy FXML and resources to target directory
rsync -a --ignore-existing src/main/resources/project/ target/project/

# Run the application with JavaFX, ensuring resources are in classpath
java --module-path "$JAVAFX_PATH" --add-modules javafx.controls,javafx.fxml -cp target project.FirePaaRadApp
