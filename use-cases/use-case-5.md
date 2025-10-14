# USE CASE: 5 Language Report
## CHARACTERISTIC INFORMATION

### Goal in Context

As a Data Analyst for an Organisation, I want to produce a report about different number of people who speak specific language and their percentage of the total world population, from greatest to smallest, so I can understand global linguistic distribution.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

The system is up and running and connected to database.
The database contains data for population value and list of spoken languages.

### Success End Condition

The system displays report for Data Analyst showing the total number of people for selected languages and their percentage of the total global population.

### Failed End Condition

No report is produced.
Error message is displayed.

### Primary Actor

Data Analyst.

### Trigger

Data Analyst selects Language Reports from the menu.

## MAIN SUCCESS SCENARIO

1. Data Analyst opens up the System.
2. The Analyst selects Languages Reports from option menu.
3. The System retrieves a list of languages:
   1. Chinese
   2. English
   3. Hindi
   4. Spanish
   5. Arabic
   From the greatest number to smallest, with percentage of the world population.
4. The System displays the report.
5. The Analyst provides the report for an organisation.

## EXTENSIONS

4. No results returned:
   1. The System displays "No data found."

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
