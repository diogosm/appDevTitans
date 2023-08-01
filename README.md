# App DevTitans
## Funcionalidades so far...

[x] Splash Screen
[x] Tela de login
[ ] Tela de login usando banco
[x] Home Activity com bottom menu
[x] Floating button pra add algo
[x] Floating button pra add algo usando banco
[x] Suporte ao SQLite
[x] Recycler view
[x] Recycler view recuperando do banco
[ ] Add suporte ao FireBase (@todo)
[ ] Add suporte a notificações
[x] Add suporte a gif
[x] Add suporte ao spotify (@todo)

## Funcionamento

### v 1.0

### Banco de dados, crie uma classe de helper para criar ou abrir o banco e retornar uma variável com uma variável para o contexto do banco. Para uso, basta instanciar o objeto dessa classe.

Classe:

```sql
public class DB  {
    private SQLiteDatabase database;

    public DB(Context context){
        database = context.openOrCreateDatabase("meu_banco.db", Context.MODE_PRIVATE,
                null);
    }

    public SQLiteDatabase getDb(){
        return this.database;
    }
}
```

Para usar, utilize um objeto da classe helper para executar queries:

```java
// passo 1. Criar o banco
// table ITEM
// colunas mood, musica
String query_cria_banco = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + "" +
        "(MOOD TEXT NOT NULL, MUSICA TEXT NOT NULL)";
//chamo a variavel de banco
DB db = new DB(context);
SQLiteDatabase database = db.getDb();
//executo a query
database.execSQL(query_cria_banco);
```

Ou receber os resultados de queries:

```java
public ArrayList<Item> getList(){
    //conecta no banco
    DB db = new DB(context);
    SQLiteDatabase database = db.getDb();

    String query_busca = "SELECT * FROM " + DATABASE_TABLE;
    Cursor cursor = database.rawQuery(query_busca, null);

    //defino uma variavel de retorno
    ArrayList<Item> itemsReposta = new ArrayList<>();

    if(cursor.moveToFirst()){
        do {
            @SuppressLint("Range") Item novoItem = new Item(
                    cursor.getString(cursor.getColumnIndex("MOOD")),
                    cursor.getString(cursor.getColumnIndex("MUSICA"))
            );
            itemsReposta.add(novoItem);
        } while(cursor.moveToNext());
    }

    Log.i("BANCO", "Consulta de busca feita!");

    return itemsReposta;
}
```

Para usar o banco de dados,

## Doc
