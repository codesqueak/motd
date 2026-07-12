# Docker-ing

This project generates docker images which are stored in the GHCR (Git Hub Container Registry)

## What you need

A personal access token (classic)

- Log into GitHub and in the upper-right corner of any page on GitHub, click your profile picture, then click Settings.
- Click Generate new token
- Follow instructions to get your token

Once you have a token, make it available locally by adding this to ```.bashrc```

```bash
export CR_PAT=<Token Goes Here>
```

Then use ```source .bashrc``` to make it visible.

Next, log into docker
```bash
echo $CR_PAT | docker login ghcr.io -u USERNAME --password-stdin
```
Docker commands should now work, e.g.
```angular2html
docker inspect ghcr.io/NAMESPACE/IMAGE_NAME
```
