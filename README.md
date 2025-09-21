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

## Local Development

### Prerequisites
- Java 17 or higher
- Docker and Docker Compose (for running the database)

### Database Setup

1. **Copy the environment configuration:**
   ```bash
   cp .env.example .env
   ```

2. **Start the PostgreSQL database:**
   ```bash
   docker-compose up -d
   ```

   This will:
   - Start PostgreSQL 16 on port 5433 (configurable via POSTGRES_PORT in .env)
   - Create a database named `almanakk_db`
   - Initialize it with a sample bookstore schema (3 tables with foreign key relationships)
   - Load sample data for testing navigation features

3. **Verify the database is running:**
   ```bash
   docker-compose ps
   ```

   You should see `almanakk-postgres` with status "running".

4. **Stop the database when done:**
   ```bash
   docker-compose down
   ```

   To completely remove the database data:
   ```bash
   docker-compose down -v
   ```

### Running the Application

1. **Start the Spring Boot application:**
   ```bash
   ./gradlew bootRun
   ```

2. **Access the application:**
   Open http://localhost:8080 in your browser

### Database Connection Details

The sample database includes:
- **Schema:** `bookstore`
- **Tables:**
  - `authors`, `publishers`, `categories` (master data)
  - `books` (with foreign keys to authors, publishers, categories)
  - `customers`, `orders`, `order_items` (transactional data)
  - `reviews`, `inventory` (supporting tables)
- **Credentials:** See `.env.example` for default values
