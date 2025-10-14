# USE CASE: 2 City Report
## CHARACTERISTIC INFORMATION

### Goal in Context

As a Data Analyst for an Organisation, I want to produce a report about cities in the world organised by continent, region, country, or district, from largest to smallest, so that I can analyse and compare population sizes, and patterns.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

The system is up and running and connected to database.
The database contains data for all cities that are linked to countries, districts, regions and continents.

### Success End Condition

The system displays report for Data Analyst showing city population data organised by the chosen filters, and sorted from largest to smallest with the following columns:
1. Name
2. Country
3. District
4. Population

### Failed End Condition

No report is produced.
Error message is displayed.

### Primary Actor

Data Analyst.

### Trigger

Data Analyst selects City Reports from the menu, and picks a filter or value.

## MAIN SUCCESS SCENARIO

1. Data Analyst opens up the System.
2. The Analyst selects City Reports from option menu.
3. The System displays filtering options for:
   1. All Cities
   2. Cities by Continent
   3. Cities by Region
   4. Cities by Country
   5. Cities by District
   6. Top N Cities (input for N)
4. The Analyst chooses the option and inputs additional requirements if needed (N value).
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
