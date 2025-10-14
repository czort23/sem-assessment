# USE CASE: 3 Capital City Report
## CHARACTERISTIC INFORMATION

### Goal in Context

As a Data Analyst for an Organisation, I want to produce a report about capital cities in the world organised by continent, region, country, or district, from largest to smallest, so that I can analyse and compare population sizes, and patterns.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

The system is up and running and connected to database.
The database contains data for all the capital cities that are linked to their countries.

### Success End Condition

The system displays report for Data Analyst showing capital city population data organised by the chosen filters, with the following columns:
1. Name
2. Country
3. Population

### Failed End Condition

No report is produced.
Error message is displayed.

### Primary Actor

Data Analyst.

### Trigger

Data Analyst selects Capital City Reports from the menu, and picks a filter or value.

## MAIN SUCCESS SCENARIO

1. Data Analyst opens up the System.
2. The Analyst selects Capital City Reports from option menu.
3. The System displays filtering options for:
   1. All Capital Cities in the World
   2. Capital Cities by Continent
   3. Capital Cities by Region
   4. Capital Cities by Country
   5. Top N Capital Cities
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
