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
- Any deployed image can be traced back to its exact source commit

## Release workflow

Every push to `main` triggers the build pipeline, which:

1. Runs tests
2. Builds the container image
3. Tags it with the short commit SHA
4. Pushes it to GHCR
5. Clones the `codesqueak/gitops` repo, updates the image tag in
   `gitops-repo/environments/dev/motd-values.yaml`, and commits/pushes that change

There's no ArgoCD Image Updater or registry-watching component involved - the CI workflow itself writes
the new tag directly into the gitops repo. ArgoCD's `motd-dev` Application then picks that commit up via
its normal git polling plus `selfHeal`, and rolls the new image out. See
[install-motd.md](https://github.com/codesqueak/gitops/blob/main/install-motd.md) in the gitops repo for
the full pipeline.

No manual tagging or version management is required.

## Gradle version

The project still uses the [axion-release-plugin](https://github.com/allegro/axion-release-plugin) for the Gradle build version (JAR filename etc.), but this is decoupled from the container image tag. The container image tag is always the commit SHA.
