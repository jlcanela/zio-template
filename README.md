# zio-template

A ZIO template project

## Configuration

Use the .env-template file as template to define your database credentials: 
```
cp .env-template .env
echo update your .env file and change DB credentials immediately
```

## Run the project

```
git clone git@github.com:jlcanela/zio-template.git
cd zio-template
docker-compose --env-file .env up # start postgres database
./mill ziotemplate.run            # run ziotemplate project
```

## Troubleshooting

If any issue, please check the [Troubleshooting](doc/troubleshooting.md) documentation.
