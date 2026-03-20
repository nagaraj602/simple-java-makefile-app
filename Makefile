# Variables
SRC_DIR=src
BUILD_DIR=build
MAIN_CLASS=Main
PORT=8087

# Default target
all: run 

# Compile Java code
build:
	mkdir -p $(BUILD_DIR)
	javac -d $(BUILD_DIR) $(SRC_DIR)/Main.java

# Run application
run: build
	@echo "🚀 Starting application..."
	@echo "👉 You can access the application at: http://$$(curl -s ifconfig.me):$(PORT)"
	java -cp $(BUILD_DIR) $(MAIN_CLASS)

# Clean build files
clean:
	rm -rf $(BUILD_DIR)

# Show access URL
url:
	@echo "http://$$(curl -s ifconfig.me):$(PORT)"
