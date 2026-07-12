# Versioning

This project uses the short git commit SHA (first 7 characters) as the container image tag. This ensures every build produces a unique, immutable tag that is traceable back to the exact source commit.

## Image tag format

```
ghcr.io/codesqueak/motd:<sha>
```

For example, a commit with SHA `a3f9c12ef...` produces:

```
ghcr.io/codesqueak/motd:a3f9c12
```

## Why commit SHA?

- Every push to `main` produces a unique tag — no manual tagging required
- Tags are immutable; the same SHA always refers to the same image
- ArgoCD Image Updater can detect new tags and trigger automatic deployments
- Any deployed image can be traced back to its exact source commit

## Release workflow

Every push to `main` triggers the build pipeline, which:

1. Runs tests
2. Builds the container image
3. Tags it with the short commit SHA
4. Pushes it to GHCR

No manual tagging or version management is required.

## Gradle version

The project still uses the [axion-release-plugin](https://github.com/allegro/axion-release-plugin) for the Gradle build version (JAR filename etc.), but this is decoupled from the container image tag. The container image tag is always the commit SHA.
