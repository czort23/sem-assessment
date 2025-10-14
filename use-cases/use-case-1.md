# USE CASE: 1 Country Report
## CHARACTERISTIC INFORMATION

### Goal in Context

As a Data Analyst for an Organisation, I want to produce a report about all countries in the world organised by continent, region so that I can analyse and compare population sizes.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

The system is up and running and connected to database.
The database contains data for all the countries, including code, name, continent, region, population and capital city.

### Success End Condition

The system displays report for Data Analyst showing all requested countries with chosen filters with the following columns:
1. Code
2. Name
3. Continent
4. Region
5. Population
6. Capital

### Failed End Condition

No report is produced.
Error message is displayed.

### Primary Actor

Data Analyst.

### Trigger

Data Analyst selects Country Reports from the menu, and picks a filter or value.

## MAIN SUCCESS SCENARIO

1. Data Analyst opens up the System.
2. The Analyst selects Country Reports from option menu.
3. The System displays filtering options for:
    1. Word
    2. Continent
    3. Region
    4. Top N Countries
4. The Analyst chooses the option and inputs additional requirements if needed (N value, continent name, etc.)
5. The System displays the report.
6. The Analyst provides the report for an organisation.

## EXTENSIONS

4. Invalid input or N value:
   1. System displays an error "Please enter a valid value"
5. No results returned:
   1. The System displays "No data found."

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
