## Almanakk

**Browse your database like an ancient reference book**

Navigate through your database by clicking foreign keys, just like following cross-references in an almanac. No SQL, no complex tools - just click and explore.

### What it does

Almanakk turns your database into a clickable, browsable book. Start at any table, click a foreign key value, and instantly navigate to that related record. Like Wikipedia for your database.

### Features

**✅ Current (v0.1):**
- Connect to PostgreSQL or MySQL databases (read-only)
- Display tables and their data in a clean, readable format
- Click any foreign key value to navigate to that record
- Breadcrumb trail: `Home > Orders > Customer #456 > Address #789`
- Back/forward navigation like a browser
- Paginate through large tables (100 rows at a time)
- Define virtual foreign keys when database lacks proper constraints
- Search for tables by name
- Remember recently viewed records

**🚧 Planned:**
- SQLite support
- Search/filter within table data
- Export current view to CSV
- Share links to specific records
- Composite foreign key support
- Multiple database connections

**❌ Intentionally excluded:**
- Schema editing
- Data modification
- SQL query builder
- Charts/visualizations
- User management
- Anything except browsing

This is a browsing tool. We keep it simple on purpose.
