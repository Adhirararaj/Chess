# Makefile for Java Chess Project with source in src/

# Java tools
JAVAC = javac
JAVA = java

# Source folder
SRC_DIR = src

# Java source files (explicitly listed paths)
SRC = $(SRC_DIR)/main.java \
      $(SRC_DIR)/BackgroundPanel.java \
      $(SRC_DIR)/Board/*.java \
      $(SRC_DIR)/ChessAI/*.java \
      $(SRC_DIR)/GameLogic/*.java \
      $(SRC_DIR)/Pieces/*.java \
      $(SRC_DIR)/Players/*.java

# Main class (no package)
MAIN_CLASS = main

.PHONY: all run clean

# Compile all Java files
all:
	$(JAVAC) $(SRC)

# Run the program
run: all
	cd $(SRC_DIR) && $(JAVA) $(MAIN_CLASS)

# Clean all .class files
clean:
	find $(SRC_DIR) -name "*.class" -delete
